package com.api.pipeline.member;

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

    public Optional<MemberEntity> getMember(Integer id){
        return memberRepository.findById(id);
    }

    public MemberEntity updateMember(String id, MemberEntity member){
        final Optional<MemberEntity> fetchedMember = memberRepository.findById(id);
        if(fetchedMember.isPresent()){
            member.setId(id);
            return memberRepository.save(member);
        }
        else{
            return null;
        }
    }

    public MemberEntity patchMember(Integer id, MemberEntity member){
        final Optional<MemberEntity> fetchedMember = memberRepository.findById(id);
        if(fetchedMember.isPresent()){
            if(member.getId() != null){
                fetchedMember.get().setName(member.getName());
            }
            if(member.getName() != null){
                fetchedMember.get().setName(member.getName());
            }
            if(member.getPassword() != null){
                fetchedMember.get().setPassword(member.getPassword());
            }
            return memberRepository.save(fetchedMember.get());
        }
        else{
            return null;
        }
    }

//    public boolean deleteUser(Long id){
//        final Optional<User> fetchedUser = userRepository.findById(id);
//        if(fetchedUser.isPresent()){
//            userRepository.deleteById(id);
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
}
