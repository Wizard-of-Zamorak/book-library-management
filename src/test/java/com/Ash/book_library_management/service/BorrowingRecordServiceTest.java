package com.Ash.book_library_management.service;

import com.Ash.book_library_management.entity.Book;
import com.Ash.book_library_management.entity.BorrowingRecord;
import com.Ash.book_library_management.entity.Member;
import com.Ash.book_library_management.entity_DTO.BorrowingRecordDTO;
import com.Ash.book_library_management.entity_Mappers.BorrowingRecordMapper;
import com.Ash.book_library_management.repository.BookRepository;
import com.Ash.book_library_management.repository.BorrowingRecordRepository;
import com.Ash.book_library_management.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowingRecordServiceTest {

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowingRecordMapper borrowingRecordMapper;

    @InjectMocks
    private BorrowingRecordServiceImpl borrowingRecordService;

    @Test
    void shouldBorrowBookSuccessfully() {

        // given
        Member member = new Member("John", "Doe", LocalDate.of(1990,1,1), true);
        member.setId(1L);

        Book book = new Book("Clean Code", null, "Programming", 2008, 3);
        book.setId(1L);

        BorrowingRecordDTO dto = new BorrowingRecordDTO();
        dto.setMemberId(1L);
        dto.setBookId(1L);
        dto.setBorrowDate(LocalDate.now());
        dto.setBorrowPeriodWeeks(2);

        BorrowingRecord record = new BorrowingRecord();
        record.setMember(member);
        record.setBook(book);

        BorrowingRecordDTO returnedDto = new BorrowingRecordDTO();

        // mock behavior
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(borrowingRecordRepository.findByMemberIdAndReturnedFalse(1L))
                .thenReturn(List.of());
        when(borrowingRecordRepository.findByMemberIdAndBookIdAndReturnedFalse(1L,1L))
                .thenReturn(List.of());
        when(borrowingRecordMapper.toEntity(any())).thenReturn(record);      // <-- add this
        when(borrowingRecordRepository.save(any())).thenReturn(record);
        when(borrowingRecordMapper.toDto(record)).thenReturn(returnedDto);

        // when
        BorrowingRecordDTO result = borrowingRecordService.borrowBook(dto);

        // then
        assertNotNull(result);
        verify(bookRepository).save(book);
    }

    @Test
    void shouldFailToBorrowWhenBookQuantityIsZero() {
        // given
        Member member = new Member("John", "Doe", LocalDate.of(1990, 1, 1), true);
        member.setId(1L);

        Book book = new Book("Clean Code", null, "Programming", 2008, 0); // quantity = 0
        book.setId(1L);

        BorrowingRecordDTO dto = new BorrowingRecordDTO();
        dto.setMemberId(member.getId());
        dto.setBookId(book.getId());
        dto.setBorrowDate(LocalDate.now());
        dto.setBorrowPeriodWeeks(2);

        // mock behavior
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        // when
        BorrowingRecordDTO result = borrowingRecordService.borrowBook(dto);

        // then
        assertNull(result);

        // verify that bookRepository.save was never called
        verify(bookRepository, never()).save(any());
    }

}
