package com.seistv.to_do_list_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seistv.to_do_list_backend.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
