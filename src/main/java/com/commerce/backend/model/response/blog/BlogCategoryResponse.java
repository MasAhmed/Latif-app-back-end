package com.commerce.backend.model.response.blog;

import com.commerce.backend.model.entity.BlogCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogCategoryResponse {
    private Long id;
    private String name;
    private String description;

    public BlogCategoryResponse(BlogCategory blogCategory) {
        this.id = blogCategory.getId();
        this.name = blogCategory.getName();
        this.description = blogCategory.getDescription();
    }
}