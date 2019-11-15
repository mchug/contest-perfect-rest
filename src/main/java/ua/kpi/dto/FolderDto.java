package ua.kpi.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class FolderDto {

    @NotBlank
    private String name;

    @Valid
    private List<DocumentDto> documents;
}
