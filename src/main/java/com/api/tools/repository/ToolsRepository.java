package com.api.tools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.tools.model.ToolsModel;

@Repository
public interface ToolsRepository  extends JpaRepository<ToolsModel, Long>{
	
//	Optional<Boolean> findAllByTitleIgnoreCase (String title);
	boolean existsByTitleIgnoreCase (String title);

}
