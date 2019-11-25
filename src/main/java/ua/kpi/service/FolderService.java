package ua.kpi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.dto.FolderDto;
import ua.kpi.entity.Folder;
import ua.kpi.entity.Project;
import ua.kpi.mapper.FolderMapper;
import ua.kpi.repository.FolderRepository;
import ua.kpi.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FolderService {

    private FolderMapper folderMapper;
    private ProjectRepository projectRepository;
    private FolderRepository folderRepository;

    public Long create(FolderDto folderDto, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
        Folder folder = folderMapper.toEntity(folderDto);
        project.addFolder(folder);
        folderRepository.save(folder);
        return folder.getId();
    }

    public void update(FolderDto folderDto,  Long folderId) {
        folderRepository.findById(folderId).orElseThrow(EntityNotFoundException::new);
        Folder folder = folderMapper.toEntity(folderDto);
        folder.setId(folderId);
        folderRepository.save(folder);
    }

    public FolderDto read(Long folderId) {
        Folder folder = folderRepository.findById(folderId).orElseThrow(EntityNotFoundException::new);
        return folderMapper.toDto(folder);
    }

    public void delete(Long folderId) {
        Folder folder = folderRepository.findById(folderId).orElseThrow(EntityNotFoundException::new);
        folder.getProject().deleteFolder(folder);
        folderRepository.deleteById(folderId);
    }
}
