package com.devsuperior.movieflix.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_review")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String text;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Review() {
	}

	public Review(Long id, String text, Movie movie, User user) {
		this.id = id;
		this.text = text;
		this.movie = movie;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public Review setId(Long id) {
		this.id = id;
		return this;
	}

	public String getText() {
		return text;
	}

	public Review setText(String text) {
		this.text = text;
		return this;
	}

	public Movie getMovie() {
		return movie;
	}

	public Review setMovie(Movie movie) {
		this.movie = movie;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Review setUser(User user) {
		this.user = user;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Review review = (Review) o;
		return Objects.equals(id, review.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
