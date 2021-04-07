package com.acciona.tweetsmanager.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acciona.tweetsmanager.model.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

	@Query("select h from Tweet t join t.hashtags h group by h.id order by count(h.id) desc")
	public List<Hashtag> findByCountUsedOrderDesc(Pageable pageable);

}
