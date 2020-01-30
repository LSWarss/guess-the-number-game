package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    private static final String MAIN_MESSAGE = "game.main.message"; //key for main message
    private static final String WIN_MESSAGE = "game.win";
    private static final String LOSE_MESSAGE = "game.lose";
    private static final String INVALID_RANGE = "game.invalid.range";
    private static final String FIST_GUESS = "game.first.guess";
    private static final String LOWER = "game.lower";
    private static final String HIGHER = "game.higher";
    private static final String GUESSES_REMAINING = "game.remaining";


    // == fields ==
    private final Game game;
    private final MessageSource messageSource;

    // == constructors ==

    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // == init ==
    @PostConstruct
    public void logger(){
        log.info("game = {}", game);
    }

    // == public methods ==
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE,game.getSmallest(),game.getBiggest());
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon()){
            return getMessage(WIN_MESSAGE, game.getNumber());
        }
        else if(game.isGameLost()){
            return getMessage(LOSE_MESSAGE, game.getNumber());
        }
        else if(!game.isValidNumberRange()){
            return getMessage(INVALID_RANGE);
        }
        else if(game.getRemainingGuesses() == game.getGuessCount()){
            return getMessage(FIST_GUESS);
        }
        else{
            String direction = getMessage(LOWER);

            if(game.getGuess() < game.getNumber()){
                direction = getMessage(HIGHER);
            }

            return getMessage(GUESSES_REMAINING, direction, game.getRemainingGuesses());
        }


    }

    // == private methods ==

    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code,args, LocaleContextHolder.getLocale());
    }


}
