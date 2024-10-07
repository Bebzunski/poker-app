package org.bebzunski.calculator.controller;

import java.math.BigDecimal;

import org.bebzunski.calculator.output.InputCards;
import org.bebzunski.calculator.services.InputCardsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/holdem")
public class PokerController {

	private final InputCardsService inputCardsService;

	@PostMapping
	public String calculate(@RequestBody InputCards inputCards) {
		inputCardsService.map(inputCards);
		return new BigDecimal("45.78").toString();
	}

}
