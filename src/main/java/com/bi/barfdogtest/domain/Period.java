package com.bi.barfdogtest.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Embeddable
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;


}
