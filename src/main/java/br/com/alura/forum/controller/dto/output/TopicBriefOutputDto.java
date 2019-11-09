package br.com.alura.forum.controller.dto.output;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;

public class TopicBriefOutputDto {
	private Long id;
	private String shortDescription;
	private Long secondSinceLastupdate;
	private String ownerName;
	private String courseName;
	private String subcategoryName;
	private String categoryName;
	private Integer numberOfResponses;
	private Boolean solved;
	
	public TopicBriefOutputDto(Topic topic) {
		this.id = topic.getId();
		this.shortDescription = topic.getShortDescription();
		this.secondSinceLastupdate = getSecondsSince(topic.getLastUpdate());
		this.ownerName = topic.getOwnerName();
		this.courseName = topic.getCourse().getName();
		this.subcategoryName = topic.getCourse().getSubcategory().getName();
		this.categoryName = topic.getCourse().getCategoryName();
		this.numberOfResponses = topic.getNumberOfAnswers();
		this.solved = TopicStatus.SOLVED.equals(topic.getStatus());
	}
	
	private long getSecondsSince(Instant lastUpdate) {
		return Duration.between(lastUpdate, Instant.now())
				.get(ChronoUnit.SECONDS);
	}	
	
	public Long getId() {
		return id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public Long getSecondSinceLastupdate() {
		return secondSinceLastupdate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Integer getNumberOfResponses() {
		return numberOfResponses;
	}

	public Boolean getSolved() {
		return solved;
	}

	public static List<TopicBriefOutputDto> listFromTopics(List<Topic> topics){
		return topics.stream()
				.map(TopicBriefOutputDto::new)
				.collect(Collectors.toList());
	}
}
