package kazior.paulina.easytest.service;


import kazior.paulina.easytest.model.Comment;
import kazior.paulina.easytest.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;



}
