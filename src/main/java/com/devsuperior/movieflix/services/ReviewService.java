package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public List<ReviewDTO> findByMovie(Long id) {
		List<Review> reviewList = repository.findByMovie(id);
		return reviewList.stream().map(review -> new ReviewDTO(review)).collect(Collectors.toList());
	}

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		entity.setText(dto.getText());

		Movie movie = new Movie();
		movie.setId(dto.getMovieId());
		entity.setMovie(movie);

		User user = authService.authenticated();
		entity.setUser(user);

		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}
}
