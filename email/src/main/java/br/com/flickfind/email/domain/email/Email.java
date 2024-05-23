package br.com.flickfind.email.domain.email;

import br.com.flickfind.email.dtos.EmailDTO;
import br.com.flickfind.email.dtos.EmailStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Entity
@Table(name = "emails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String emailTo;
    @Column(nullable = false)
    private String emailFrom;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;
    @Column(nullable = false)
    private LocalDateTime sendEmailDate;
    @Column(nullable = false)
    private EmailStatus emailStatus;

    public Email(EmailDTO emailDTO) {
        BeanUtils.copyProperties(emailDTO, this);
    }

}
