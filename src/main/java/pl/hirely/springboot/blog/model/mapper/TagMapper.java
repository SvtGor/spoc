package pl.hirely.springboot.blog.model.mapper;

import org.springframework.stereotype.Component;
import pl.hirely.springboot.blog.model.domain.BlogPost;
import pl.hirely.springboot.blog.model.domain.Tag;
import pl.hirely.springboot.blog.model.dto.TagPostDto;

import java.util.stream.Collectors;

@Component
public class TagMapper {

    public TagPostDto mapTagEntityToTagPostDto(Tag tag) {
        return new TagPostDto(tag.getName(), tag.getPosts()
                .stream()
                .map(BlogPost::getTitle)
                .collect(Collectors.toList()));
    }
}
