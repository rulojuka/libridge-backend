package br.com.sbk.sbking.core;

import static br.com.sbk.sbking.core.GameConstants.TOTAL_GAMES;

import java.util.HashSet;
import java.util.Set;

import br.com.sbk.sbking.core.rulesets.abstractClasses.NegativeRuleset;
import br.com.sbk.sbking.core.rulesets.abstractClasses.PositiveRuleset;
import br.com.sbk.sbking.core.rulesets.abstractClasses.Ruleset;
import br.com.sbk.sbking.gui.models.KingGameScoreboard;

public class PositiveKingGame extends TrickGame {

	private KingGameScoreboard gameScoreboard;
	private Set<NegativeRuleset> chosenNegativeRulesets = new HashSet<NegativeRuleset>();
	private int northSouthNegatives = 0;
	private int eastWestNegatives = 0;
	private int northSouthPositives = 0;
	private int eastWestPositives = 0;
	private int playedHands = 0;

	public PositiveKingGame() {
		this.gameScoreboard = new KingGameScoreboard();
		this.dealNewBoard();
		
	}

	public void addRuleset(Ruleset currentGameModeOrStrain) {
		this.currentDeal = new Deal(this.currentBoard, currentGameModeOrStrain);
	}

	@Override
	public boolean isFinished() {
		return playedHands == TOTAL_GAMES;
	}

	@Override
	public void finishDeal() {
		Direction currentChooser = this.getCurrentDeal().getDealer().getPositiveOrNegativeChooserWhenDealer();
		boolean isNorthSouth = currentChooser.isNorthSouth();
		Ruleset currentRuleset = this.getCurrentDeal().getRuleset();
		boolean isPositive = currentRuleset instanceof PositiveRuleset;

		this.getGameScoreboard().addFinishedDeal(this.getCurrentDeal());
		if (isPositive) {
			if (isNorthSouth) {
				northSouthPositives++;
			} else {
				eastWestPositives++;
			}
		} else {
			NegativeRuleset negativeRuleset = (NegativeRuleset) currentRuleset;
			this.chosenNegativeRulesets.add(negativeRuleset);
			if (isNorthSouth) {
				northSouthNegatives++;
			} else {
				eastWestNegatives++;
			}
		}
		this.dealer = this.dealer.next();
		this.playedHands++;
	}

	public KingGameScoreboard getGameScoreboard() {
		return gameScoreboard;
	}

	public boolean isGameModePermitted(Ruleset ruleset, Direction chooser) {
		return (ruleset instanceof PositiveRuleset);
	}

}