package com.javarush.task.task35.task3513;

/**
 * В методе compareTo первым делом сравни количество пустых плиток (numberOfEmptyTiles), потом счет (score),
 * если количество пустых плиток равное. Если и счет окажется равным, будем считать эффективность ходов равной и вернем ноль.
 */
public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency another) {
        if (numberOfEmptyTiles == another.numberOfEmptyTiles)
                return Integer.compare(score, another.score);
         else
            return Integer.compare(numberOfEmptyTiles, another.numberOfEmptyTiles);

    }
}
