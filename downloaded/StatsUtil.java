package com.scc.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scc.model.Player;
import com.scc.model.PlayerStatPerMatch;

public class StatsUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(StatsUtil.class);

	public static Player calculateBattingAverage(Player player) {
		double battingAverage = 0;
		try {
			battingAverage = (1.00 * player.getBattingStats().getTotalRunsScored())
					/ (player.getBattingStats().getInningsBatted() - player.getBattingStats().getNotOuts());
			String df = String.format("%.2f", battingAverage);
			battingAverage = Double.parseDouble(df);
		} catch (ArithmeticException e) {
			LOGGER.error(
					"Arithmetic exception while calculating batting average, default setting to 0 for player name : {}",
					player.getPlayerName());
		}
		player.getBattingStats().setBattingAverage(battingAverage);
		return player;
	}

	public static Player calculateEconomyRate(Player player) {
		double economyRate = 0;
		try {
			if(player.getBowlingStats().getOversBowled()>0) {
				economyRate = (1.00 * player.getBowlingStats().getTotalRunsGiven())
						/ player.getBowlingStats().getOversBowled();
				String df = String.format("%.2f", economyRate);
				economyRate = Double.parseDouble(df);
			}
		} catch (ArithmeticException e) {
			LOGGER.error("Arithmetic exception while calculating economy, default setting to 0 for player name : {}",
					player.getPlayerName());
		}
		player.getBowlingStats().setEconomyRate(economyRate);
		return player;
	}

	public static Player calculateStrikeRate(Player player) {
		double strikeRate = 0;
		try {
			if(player.getBattingStats().getTotalBallsPlayed() > 0) {
				strikeRate = (100.00 * player.getBattingStats().getTotalRunsScored())
						/ (player.getBattingStats().getTotalBallsPlayed());
				String df = String.format("%.2f", strikeRate);
				strikeRate = Double.parseDouble(df);
			}
		} catch (ArithmeticException e) {
			LOGGER.error(
					"Arithmetic exception while calculating strike rate, default setting to 0 for player name : {}",
					player.getPlayerName());
		}
		player.getBattingStats().setStrikeRate(strikeRate);
		return player;
	}

	public static Long calculateTotalNumberOfNotOuts(List<PlayerStatPerMatch> matchRecords) {
		Long inningsBatted = 0L;
		inningsBatted = matchRecords.stream().filter(record -> record.hasBatted() && record.isNotOut()).count();
		return inningsBatted;
	}

	public static Long calculateTotalNumberOfRunsScored(List<PlayerStatPerMatch> matchRecords) {
		Long runsScored = 0L;
		runsScored = matchRecords.stream().filter(record -> record.hasBatted() && record.getRunsScored() > 0)
				.mapToLong(PlayerStatPerMatch::getRunsScored).sum();
		return runsScored;
	}

	public static Long calculateTotalNumberOfTimesBatted(List<PlayerStatPerMatch> matchRecords) {
		Long timesBatted = 0L;
		timesBatted = matchRecords.stream().filter(record -> record.hasBatted()).count();
		return timesBatted;
	}

	public static Long calculateTotalBallsPlayed(List<PlayerStatPerMatch> matchRecords) {
		Long ballsPlayed = 0L;
		ballsPlayed = matchRecords.stream().filter(record -> record.hasBatted() && record.getBallsPlayed() > 0)
				.mapToLong(PlayerStatPerMatch::getBallsPlayed).sum();
		return ballsPlayed;
	}

	public static Long calculateTotalMatchesPlayed(List<PlayerStatPerMatch> matchRecords) {
		return matchRecords.stream().filter(record -> record.hasBatted() || record.hasBowled()).count();
	}

	public static Long calculateTotalNumberOfRunsGiven(List<PlayerStatPerMatch> matchRecords) {
		Long runsGiven = 0L;
		runsGiven = matchRecords.stream().filter(record -> record.hasBowled() && record.getRunsGiven() > 0)
				.mapToLong(PlayerStatPerMatch::getRunsGiven).sum();
		return runsGiven;
	}
}
