package club.libridge.libridgebackend.core

import club.libridge.libridgebackend.core.CompleteHand
import club.libridge.libridgebackend.core.Direction
import club.libridge.libridgebackend.core.GameConstants
import club.libridge.libridgebackend.core.Validated

case class CompleteDeckInFourHands(hands: Map[Direction,CompleteHand]) extends Validated[CompleteDeckInFourHands]:
    import CompleteDeckInFourHands._

    override def getValid(): Either[ Iterable[Exception], CompleteDeckInFourHands ] = validate(this.hands)
    
    def getHandOf(direction:Direction) = {
        this.hands.get(direction).get
    }

end CompleteDeckInFourHands
case object CompleteDeckInFourHands:
    private val MISSING_HAND = "A plastic board must be constructed with a complete hand for all directions."
    private val MISSING_CARD = "A plastic board must be constructed with all cards of the deck."
    private val TOTAL_NUMBER_OF_CARDS = GameConstants.NUMBER_OF_HANDS * GameConstants.SIZE_OF_HAND

    private def validate(hands: Map[Direction,CompleteHand]): Either[ Iterable[Exception], CompleteDeckInFourHands ] = {
        val (exceptions, validatedCompleteHands) = Direction.values.toList
            .map((direction) => hands.get(direction))
            .partitionMap((handOption) => {
                handOption match {
                    case None => Left(Iterable(new IllegalArgumentException(MISSING_HAND)))
                    case Some(completeHand) => completeHand.getValid()
                }
            })
        val numberOfCards = validatedCompleteHands.map((hand) => hand.cards).fold(Set.empty)(_ union _).size
        val allExceptions = if(numberOfCards != TOTAL_NUMBER_OF_CARDS){
            new IllegalArgumentException(MISSING_CARD) +: exceptions.flatten
        } else {
            exceptions.flatten
        }
        if(allExceptions.isEmpty){
            Right(CompleteDeckInFourHands(hands))
        } else {
            Left(allExceptions)
        }
    }
end CompleteDeckInFourHands