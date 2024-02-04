package com.kauanlima.mongospringsocial.config;

import com.kauanlima.mongospringsocial.domain.Comments;
import com.kauanlima.mongospringsocial.domain.Post;
import com.kauanlima.mongospringsocial.domain.User;
import com.kauanlima.mongospringsocial.dto.AuthorDTO;
import com.kauanlima.mongospringsocial.dto.CommentDTO;
import com.kauanlima.mongospringsocial.repository.PostRepository;
import com.kauanlima.mongospringsocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com", "mariamaria");
        User alex = new User(null, "Alex Green", "alex@gmail.com", "alexalex");
        User bob = new User(null, "Bob Grey", "bob@gmail.com","bobbob");
        
        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null,  "Partiu viagem!","Vou viajar para Salvador. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, "Comecei a faculdade!","Passei no Curso de Analise e Desenvolvimento de Sistemas!!!", new AuthorDTO(alex));

        Comments c1 = new Comments(null,"Boa viagem mano!", new AuthorDTO(alex) );
        Comments c2 = new Comments(null,"Aproveite!", new AuthorDTO(bob) );
        Comments c3 = new Comments(null,"Parabéns Alex!", new AuthorDTO(maria) );

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1));

        userRepository.save(maria);

        alex.getPosts().addAll(Arrays.asList(post2));
        userRepository.save(alex);


    }
}
