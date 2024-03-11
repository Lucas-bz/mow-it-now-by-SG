package com.bouzidi.samples.mowitnow.controller;


import com.bouzidi.samples.mowitnow.domain.Lawn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Data to be sent by the controller
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MowItNowResponse {

    private Lawn initial;
    private Lawn last;
}
