package kz.aldi.chat.repositories;

import kz.aldi.chat.models.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
