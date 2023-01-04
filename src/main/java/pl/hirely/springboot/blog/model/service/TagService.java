package pl.hirely.springboot.blog.model.service;

import org.springframework.stereotype.Service;
import pl.hirely.springboot.blog.model.domain.Tag;
import pl.hirely.springboot.blog.model.dto.TagPostDto;
import pl.hirely.springboot.blog.model.mapper.TagMapper;
import pl.hirely.springboot.blog.model.repository.TagRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public Set<Tag> findOrCreate(Set<String> tagNames) {
        Set<Tag> existingTags = tagRepository.findAllByNameIn(tagNames);

        return tagNames.stream()
                .map(tagName -> findExistingTagOrCreateNew(existingTags, tagName))
                .collect(Collectors.toSet());
    }

    public List<TagPostDto> findAllTagsWithPosts() {
        return tagRepository.findTagsWithPosts().stream()
                .map(tagMapper::mapTagEntityToTagPostDto)
                .collect(Collectors.toList());
    }

    private Tag findExistingTagOrCreateNew(Set<Tag> existingTags, String tagToFind) {
        return existingTags.stream()
                .filter(tag -> tag.getName().equals(tagToFind))
                .findFirst()
                .orElse(Tag.fromName(tagToFind));
    }
}
