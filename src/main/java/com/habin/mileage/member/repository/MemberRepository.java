package com.habin.mileage.member.repository;

import com.habin.mileage.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>, QMemberRepository {
}
