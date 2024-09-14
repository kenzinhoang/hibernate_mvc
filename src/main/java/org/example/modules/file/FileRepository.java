package org.example.modules.file;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileCustom, Integer> {

}
