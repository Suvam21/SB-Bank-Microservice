package com.sb_bank.cards.service.impl;

import com.sb_bank.cards.constants.CardsConstants;
import com.sb_bank.cards.dto.CardsDTO;
import com.sb_bank.cards.entity.Cards;
import com.sb_bank.cards.exception.CardAlreadyExistsException;
import com.sb_bank.cards.exception.ResourceNotFoundException;
import com.sb_bank.cards.mapper.CardsMapper;
import com.sb_bank.cards.repository.CardsRepository;
import com.sb_bank.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    CardsRepository cardsRepository;

    /**
     * @param mobile - mobile number of the customer
     */
    @Override
    public void createCard(String mobile) {
        Optional<Cards> card = cardsRepository.findByMobile(mobile);
        if(card.isPresent()){
            throw new CardAlreadyExistsException("Card already exists with mobile number: " + mobile);
        }
        cardsRepository.save(createNewCard(mobile));
    }

    /**
     * Create card for the customer
     * @param mobile - CustomerDTO object
     */
    private Cards createNewCard(String mobile){
        Random random = new Random();
        Cards newCard = new Cards();
        newCard.setMobile(mobile);
        newCard.setCardNumber(String.valueOf(1_000_000_000_000_000L + random.nextLong(9_000_000_000_000_000L)));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param mobile - mobile number of the customer
     * @return - Card Details associated with the mobile number
     */
    @Override
    public CardsDTO fetchCardDetails(String mobile) {
        Cards cards = cardsRepository.findByMobile(mobile).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number", mobile)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDTO());
    }

    /**
     * @param cardsDTO - CardDTO object
     * @return boolean
     */
    @Override
    public boolean updateCard(CardsDTO cardsDTO) {
        Cards card = cardsRepository.findByMobile(cardsDTO.getMobile()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number", cardsDTO.getMobile())
        );
        CardsMapper.mapToCards(cardsDTO, card);
        cardsRepository.save(card);
        return true;
    }

    /**
     * @param mobile - Mobile Number
     * @return boolean
     */
    @Override
    public boolean deleteCard(String mobile) {
        Cards card = cardsRepository.findByMobile(mobile).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number", mobile)
        );
        cardsRepository.delete(card);
        return true;
    }
}
