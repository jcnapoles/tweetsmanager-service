package com.acciona.tweetsmanager.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tweet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_str")
	private String idStr;

	@Lob
	@Column
	private String text;

	private Boolean validation;

	@Enumerated(value = EnumType.STRING)
	private Language language;

	@OneToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToOne(fetch = FetchType.EAGER)
	private Place place;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tweet_hashtag", joinColumns = @JoinColumn(name = "tweet_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "hashtag_id", referencedColumnName = "ID"))
	private Set<Hashtag> hashtags;
}
