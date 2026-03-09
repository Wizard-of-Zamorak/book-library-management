package com.Ash.book_library_management.service;

import com.Ash.book_library_management.entity.Member;
import com.Ash.book_library_management.entity_DTO.MemberDTO;
import com.Ash.book_library_management.entity_Mappers.MemberMapper;
import com.Ash.book_library_management.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) return null;
        return memberMapper.toDto(member);
    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member createdMember = memberMapper.toEntity(memberDTO);
        Member savedMember = memberRepository.save(createdMember);
        return memberMapper.toDto(savedMember);
    }

    @Override
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        Optional<Member> existingMemberOpt = memberRepository.findById(id);

        if (existingMemberOpt.isPresent()) {
            Member existingMember = existingMemberOpt.get(); // unwrap Optional
            existingMember.setFirstName(memberDTO.getFirstName());
            existingMember.setLastName(memberDTO.getLastName());
            existingMember.setDateOfBirth(memberDTO.getDateOfBirth());
            existingMember.setActive(memberDTO.isActive());

            Member updatedMember = memberRepository.save(existingMember);
            return memberMapper.toDto(updatedMember);
        }

        return null;
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public MemberDTO activateMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        member.setActive(true);
        memberRepository.save(member);
        return memberMapper.toDto(member);
    }

    @Override
    public MemberDTO deactivateMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        member.setActive(false);
        memberRepository.save(member);
        return memberMapper.toDto(member);
    }
}
