package com.habin.marketboro_mileage_task.repository;

import com.habin.marketboro_mileage_task.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
