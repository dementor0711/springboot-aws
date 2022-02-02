package com.dementor.springbootaws.service;

import com.dementor.springbootaws.domain.posts.Posts;
import com.dementor.springbootaws.domain.posts.PostsRepository;
import com.dementor.springbootaws.web.dto.PostsListResponseDto;
import com.dementor.springbootaws.web.dto.PostsResponseDto;
import com.dementor.springbootaws.web.dto.PostsSaveRequestDto;
import com.dementor.springbootaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto dto) {
        Posts post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        post.update(dto.getTitle(), dto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts dto = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        return new PostsResponseDto(dto);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }
}
