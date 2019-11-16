package br.com.alura.forum.controller.dto.input;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.forum.model.topic.domain.TopicStatus;
import br.com.alura.forum.model.topic.domain.Topic;

public class TopicSearchInputDto {
	private TopicStatus status;
	private String categoryName;	
	
	public TopicStatus getStatus() {
		return status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setStatus(TopicStatus status) {
		this.status = status;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Specification<Topic> build(){
		return (root, query, criteriaBuilder) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			
			if(this.status!=null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), this.status));
			}
			
			if(this.categoryName != null) {
				predicates.add(criteriaBuilder.equal(
						root.get("course").get("subcategory").get("category").get("name")
						, this.categoryName));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
	
}
