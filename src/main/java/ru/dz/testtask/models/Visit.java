package ru.dz.testtask.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Data
@Builder
@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String pageId;

    @Temporal(TemporalType.DATE)
    private Date date;
}
