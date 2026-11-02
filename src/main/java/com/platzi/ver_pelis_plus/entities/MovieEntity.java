	package com.platzi.ver_pelis_plus.entities;
	
	import java.math.BigDecimal;
	import java.time.LocalDate;
	
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	
	
	@Entity
	@Table(name = "movies")
	public class MovieEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "TITLE", nullable = false, unique = true)
		private String title;
		
		@Column(name = "DURATION", nullable = false, precision = 3)
		private Integer duration;
		
		@Column(name = "GENRE", nullable = false)
		private String genre;
		
		@Column(name = "DATE")
		private LocalDate date;
		
		@Column(name = "CLASSIFICATION", precision = 3, scale = 2)
		private BigDecimal classification;
		
		@Column(name = "STATUS", nullable = false, length = 1)
		private String status;	
		
		public MovieEntity() {}
		
		public MovieEntity(String title, Integer duration, String genre, LocalDate date, BigDecimal classification, String status) {
			this.title = title;
			this.duration = duration;
			this.genre = genre;
			this.date = date;
			this.classification = classification;
			this.status = status;
		}
		
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public Integer getDuration() {
			return duration;
		}
		
		public void setDuration(Integer duration) {
			this.duration = duration;
		}
		
		public String getGenre() {
			return genre;
		}
		
		public void setGenre(String genre) {
			this.genre = genre;
		}
		
		public LocalDate getDate() {
			return date;
		}
		
		public void setDate(LocalDate date) {
			this.date = date;
		}
		
		public BigDecimal getClassification() {
			return classification;
		}
		
		public void setClassification(BigDecimal classification) {
			this.classification = classification;
		}
		
		public String getStatus() {
			return status;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}
		
	
	}
