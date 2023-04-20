package com.example.directory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.directory.entity.Directory;
import com.example.directory.entity.Email;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory,Integer> {
	
	public final static String chat="SELECT d FROM Directory d WHERE d.directoryId IN ("
			+"SELECT e.directory FROM Email e WHERE e.email iLIKE %?1%"
			+" UNION SELECT c.directory FROM Contact c WHERE c.no LIKE %?1%"
			+" UNION select a.directory from  Address  a  where a.state ilike %?1% OR  a.city ilike %?1%"
			+ "UNION select d.directoryId from Directory d where d.fullName ilike %?1%)";	
	
	@Query(chat)
	List<Directory> findByContaining(String instance);
	
//	@Query('SELECT e FROM Email e where e.directory =?1')
//	List<Email> findByDirectoryId(int directoryId);
}
