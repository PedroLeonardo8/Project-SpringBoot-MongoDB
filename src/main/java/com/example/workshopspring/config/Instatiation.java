package com.example.workshopspring.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopspring.domain.Post;
import com.example.workshopspring.domain.User;
import com.example.workshopspring.dto.AuthorDTO;
import com.example.workshopspring.dto.CommentDTO;
import com.example.workshopspring.repositories.PostRepository;
import com.example.workshopspring.repositories.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		User maria = new User(null, "Maria", "maria@gmail.com");
		User alex = new User(null, "Alex", "alex@gmail.com");
		User roberta = new User(null, "Roberta", "roberta@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, roberta));
		
		Post post1 = new Post(null, sdf.parse("2018/03/21"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("2018/03/23"), "Bom dia", "Acordei feliz hoje!",new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("2018/03/21"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("2018-03-22"), new AuthorDTO(roberta));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("2018-03-23"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
				
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
	}

}
