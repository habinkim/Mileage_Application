package com.habin.marketboro_mileage_task.member.repository;

import com.habin.marketboro_mileage_task.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>, QMemberRepository {
}
