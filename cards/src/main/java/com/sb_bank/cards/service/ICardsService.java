package com.sb_bank.cards.service;

import com.sb_bank.cards.dto.CardsDTO;

public interface ICardsService {

    /**
     * @param mobile - mobile number of the customer
     */
    public void createCard(String mobile);

    /**
     * @param mobile - mobile number of the customer
     * @return - Card Details associated with the mobile number
     */
    CardsDTO fetchCardDetails(String mobile);

    /**
     * @param cardsDTO - CardDTO object
     * @return boolean
     */
    boolean updateCard(CardsDTO cardsDTO);

    /**
     * @param mobile - Mobile Number
     * @return boolean
     */
    boolean deleteCard(String mobile);
}
