package com.api.pipeline.blackup.service;

import com.api.pipeline.blackup.entity.MemberEntity;
import com.api.pipeline.blackup.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public boolean createMember (MemberEntity member) {
        try {
            memberRepository.save(member);
            return true;
        } catch (Exception e) {
            System.out.println("createMember Exception=>" + e);
            return false;
        }

    }

    public Optional<MemberEntity> getMember(Integer mem_id){
        return memberRepository.findById(mem_id);
    }

    public MemberEntity updateMember(Integer mem_id, MemberEntity member){
        final Optional<MemberEntity> fetchedMember = memberRepository.findById(mem_id);
        if(fetchedMember.isPresent()){
            member.setMem_id(mem_id);
            if(fetchedMember.isEmpty() != false){
                fetchedMember.get().setName(member.getName());
            }
            return memberRepository.save(member);
        }
        else{
            return null;
        }
    }

    public boolean deleteMember(Integer mem_id){
        final Optional<MemberEntity> fetchedMember = memberRepository.findById(mem_id);
        if(fetchedMember.isPresent()){
            memberRepository.deleteById(mem_id);
            return true;
        }
        else{
            return false;
        }
    }
}
