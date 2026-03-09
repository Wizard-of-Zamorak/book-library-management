package com.Ash.book_library_management.service;

import com.Ash.book_library_management.entity.Book;
import com.Ash.book_library_management.entity.BorrowingRecord;
import com.Ash.book_library_management.entity.Member;
import com.Ash.book_library_management.entity_DTO.BorrowingRecordDTO;
import com.Ash.book_library_management.entity_Mappers.BorrowingRecordMapper;
import com.Ash.book_library_management.repository.BookRepository;
import com.Ash.book_library_management.repository.BorrowingRecordRepository;
import com.Ash.book_library_management.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService{

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BorrowingRecordMapper borrowingRecordMapper;

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public BorrowingRecordServiceImpl(BorrowingRecordRepository borrowingRecordRepository,
                                      BorrowingRecordMapper borrowingRecordMapper,
                                      MemberRepository memberRepository,
                                      BookRepository bookRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.borrowingRecordMapper = borrowingRecordMapper;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BorrowingRecordDTO borrowBook(BorrowingRecordDTO borrowingRecordDTO) {

        Member member = memberRepository.findById(borrowingRecordDTO.getMemberId()).orElse(null);
        Book book = bookRepository.findById(borrowingRecordDTO.getBookId()).orElse(null);

        if (member == null || !member.isActive() || book == null || book.getQuantity() <= 0) {
            return null;
        }

        List<BorrowingRecord> activeBorrowings = borrowingRecordRepository
                .findByMemberIdAndReturnedFalse(member.getId());
        if (activeBorrowings.size() >= 3) {
            return null;
        }

        List<BorrowingRecord> sameBookActive = borrowingRecordRepository
                .findByMemberIdAndBookIdAndReturnedFalse(member.getId(), book.getId());
        if (!sameBookActive.isEmpty()) {
            return null;
        }


        BorrowingRecord borrowingRecord = borrowingRecordMapper.toEntity(borrowingRecordDTO);

        borrowingRecord.setMember(member);
        borrowingRecord.setBook(book);
        borrowingRecord.setBorrowDate(borrowingRecordDTO.getBorrowDate());
        borrowingRecord.setReturnDate(borrowingRecordDTO.getBorrowDate().plusWeeks(borrowingRecordDTO.getBorrowPeriodWeeks()));
        borrowingRecord.setReturned(false);

        BorrowingRecord savedBorrowingRecord = borrowingRecordRepository.save(borrowingRecord);

        book.setQuantity(book.getQuantity()-1);
        bookRepository.save(book);

        return borrowingRecordMapper.toDto(savedBorrowingRecord);
    }

    @Override
    public BorrowingRecordDTO returnBook(Long borrowingRecordId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(borrowingRecordId).orElse(null);

        if (borrowingRecord.isReturned()) {
            return null;
        }

        borrowingRecord.setReturned(true);

        Book book = borrowingRecord.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        borrowingRecordRepository.save(borrowingRecord);

        return borrowingRecordMapper.toDto(borrowingRecord);
    }

    @Override
    public List<BorrowingRecordDTO> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll()
                .stream()
                .map(borrowingRecordMapper::toDto)
                .collect(Collectors.toList());
    }
}
