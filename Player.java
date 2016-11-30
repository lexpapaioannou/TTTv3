/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lex
 */
public class Player {
    public class Player {

        Random random = new Random();        
        char mark;
        int Lvl;

        public Player(char mark, int Lvl) {
            this.mark = mark;
            this.Lvl = Lvl;
        }

        public void aiMove() {
            int comp;
            Scanner scanner = new Scanner(System.in);
            if (Lvl == -1) {
                while (true) {
                    System.out.println("Player "+mark+", enter your move: ");
                    int nextMove = scanner.nextInt();
                    if (nextMove == 9) {
                        System.out.println("DEBUG: Program has closed.");
                        System.exit(0);
                    }
                    if (nextMove < 0 || nextMove > 8) {
                        System.out.println("Invalid move.  You can only select a location between 0 and 8!");
                        continue;
                    }
                    if (board[nextMove] != ' ') {
                        System.out.println("Invalid move.  You can only select a location which has not yet been taken!");
                        continue;
                    }
                    
                    board[nextMove] = mark;
                    break;
                }
            } else if (Lvl == 0) {
                while (true) {
                    comp = random.nextInt(9);
                    if (board[comp] == ' ') {
                        board[comp] = mark;
                        break;
                    }

                }

            } else if (Lvl == 1) {
                while (true) {
                    comp = random.nextInt(24);
                    if (board[boardWeighted[comp]] == ' ') {
                        board[boardWeighted[comp]] = mark;
                        break;
                    }

                }

            } else if (Lvl == 2) {
                if (!aiMoveWin()) {
                    if (!aiMoveAvoidLoss()) {
                        while (true) {
                            comp = random.nextInt(24);
                            if (board[boardWeighted[comp]] == ' ') {
                                board[boardWeighted[comp]] = mark;
                                break;
                            }
                        }
                    }
                }
            }
        }

        public boolean aiMoveWin() {

            if (board[4] == 'O') {
                if (board[0] == 'O') {
                    if (board[8] == ' ') {
                        board[8] = 'O';
                        return true;
                    }
                } else if (board[1] == 'O') {
                    if (board[7] == ' ') {
                        board[7] = 'O';
                        return true;
                    }
                } else if (board[2] == 'O') {
                    if (board[6] == ' ') {
                        board[6] = 'O';
                        return true;
                    }
                } else if (board[3] == 'O') {
                    if (board[5] == ' ') {
                        board[5] = 'O';
                        return true;
                    }
                } else if (board[5] == 'O') {
                    if (board[3] == ' ') {
                        board[3] = 'O';
                        return true;
                    }
                } else if (board[6] == 'O') {
                    if (board[2] == ' ') {
                        board[2] = 'O';
                        return true;
                    }
                } else if (board[7] == 'O') {
                    if (board[1] == ' ') {
                        board[1] = 'O';
                        return true;
                    }
                } else if (board[8] == 'O') {
                    if (board[0] == ' ') {
                        board[0] = 'O';
                        return true;
                    }
                }
            } else if (board[0] == 'O') {
                if (board[1] == 'O') {
                    if (board[2] == ' ') {
                        board[2] = 'O';
                        return true;
                    }
                } else if (board[2] == 'O') {
                    if (board[1] == ' ') {
                        board[1] = 'O';
                        return true;
                    }
                } else if (board[3] == 'O') {
                    if (board[6] == ' ') {
                        board[6] = 'O';
                        return true;
                    }
                } else if (board[6] == 'O') {
                    if (board[3] == ' ') {
                        board[3] = 'O';
                        return true;
                    }
                } else if (board[4] == 'O') {
                    if (board[8] == ' ') {
                        board[8] = 'O';
                        return true;
                    }
                } else if (board[8] == 'O') {
                    if (board[4] == ' ') {
                        board[4] = 'O';
                        return true;
                    }
                }

            } else if (board[8] == 'O') {
                if (board[5] == 'O') {
                    if (board[2] == ' ') {
                        board[2] = 'O';
                        return true;
                    }
                } else if (board[2] == 'O') {
                    if (board[5] == ' ') {
                        board[5] = 'O';
                        return true;
                    }
                } else if (board[7] == 'O') {
                    if (board[6] == ' ') {
                        board[6] = 'O';
                        return true;
                    }
                } else if (board[6] == 'O') {
                    if (board[7] == ' ') {
                        board[7] = 'O';
                        return true;
                    }
                } else if (board[0] == 'O') {
                    if (board[4] == ' ') {
                        board[4] = 'O';
                        return true;
                    }
                } else if (board[4] == 'O') {
                    if (board[0] == ' ') {
                        board[0] = 'O';
                        return true;
                    }
                }
            } else if (board[1] == 'O') {
                if (board[2] == 'O') {
                    if (board[0] == ' ') {
                        board[0] = 'O';
                        return true;
                    }
                } else if (board[7] == 'O') {
                    if (board[4] == ' ') {
                        board[4] = 'O';
                        return true;
                    }
                }
            } else if (board[3] == 'O') {
                if (board[5] == 'O') {
                    if (board[4] == ' ') {
                        board[4] = 'O';
                        return true;
                    }
                } else if (board[6] == 'O') {
                    if (board[0] == ' ') {
                        board[0] = 'O';
                        return true;
                    }
                }
            } else if (board[2] == 'O') {
                if (board[5] == 'O') {
                    if (board[8] == ' ') {
                        board[8] = 'O';
                        return true;
                    }
                }
            } else if (board[6] == 'O') {
                if (board[7] == 'O') {
                    if (board[8] == ' ') {
                        board[8] = 'O';
                        return true;
                    }
                }
            }
            return false;
        }        

        public boolean aiMoveAvoidLoss() {

            if (board[4] == 'X') {
                if (board[0] == 'X') {
                    if (board[8] == ' ') {
                        board[8] = 'O';
                        return true;
                    }
                } else if (board[1] == 'X') {
                    if (board[7] == ' ') {
                        board[7] = 'O';
                        return true;
                    }
                } else if (board[2] == 'X') {
                    if (board[6] == ' ') {
                        board[6] = 'O';
                        return true;
                    }
                } else if (board[3] == 'X') {
                    if (board[5] == ' ') {
                        board[5] = 'O';
                        return true;
                    }
                } else if (board[5] == 'X') {
                    if (board[3] == ' ') {
                        board[3] = 'O';
                        return true;
                    }
                } else if (board[6] == 'X') {
                    if (board[2] == ' ') {
                        board[2] = 'O';
                        return true;
                    }
                } else if (board[7] == 'X') {
                    if (board[1] == ' ') {
                        board[1] = 'O';
                        return true;
                    }
                } else if (board[8] == 'X') {
                    if (board[0] == ' ') {
                        board[0] = 'O';
                        return true;
                    }
                }
            } else if (board[0] == 'X') {
                if (board[1] == 'X') {
                    if (board[2] == ' ') {
                        board[2] = 'O';
                        return true;
                    }
                } else if (board[2] == 'X') {
                    if (board[1] == ' ') {
                        board[1] = 'O';
                        return true;
                    }
                } else if (board[3] == 'X') {
                    if (board[6] == ' ') {
                        board[6] = 'O';
                        return true;
                    }
                } else if (board[6] == 'X') {
                    if (board[3] == ' ') {
                        board[3] = 'O';
                        return true;
                    }
                } else if (board[4] == 'X') {
                    if (board[8] == ' ') {
                        board[8] = 'O';
                        return true;
                    }
                } else if (board[8] == 'X') {
                    if (board[4] == ' ') {
                        board[4] = 'O';
                        return true;
                    }
                }

            } else if (board[8] == 'X') {
                if (board[5] == 'X') {
                    if (board[2] == ' ') {
                        board[2] = 'O';
                        return true;
                    }
                } else if (board[2] == 'X') {
                    if (board[5] == ' ') {
                        board[5] = 'O';
                        return true;
                    }
                } else if (board[7] == 'X') {
                    if (board[6] == ' ') {
                        board[6] = 'O';
                        return true;
                    }
                } else if (board[6] == 'X') {
                    if (board[7] == ' ') {
                        board[7] = 'O';
                        return true;
                    }
                } else if (board[0] == 'X') {
                    if (board[4] == ' ') {
                        board[4] = 'O';
                        return true;
                    }
                } else if (board[4] == 'X') {
                    if (board[0] == ' ') {
                        board[0] = 'O';
                        return true;
                    }
                }
            } else if (board[1] == 'X') {
                if (board[2] == 'X') {
                    if (board[0] == ' ') {
                        board[0] = 'O';
                        return true;
                    }
                } else if (board[7] == 'X') {
                    if (board[4] == ' ') {
                        board[4] = 'O';
                        return true;
                    }
                }
            } else if (board[3] == 'X') {
                if (board[5] == 'X') {
                    if (board[4] == ' ') {
                        board[4] = 'O';
                        return true;
                    }
                } else if (board[6] == 'X') {
                    if (board[0] == ' ') {
                        board[0] = 'O';
                        return true;
                    }
                }
            } else if (board[2] == 'X') {
                if (board[5] == 'X') {
                    if (board[8] == ' ') {
                        board[8] = 'O';
                        return true;
                    }
                }
            } else if (board[6] == 'X') {
                if (board[7] == 'X') {
                    if (board[8] == ' ') {
                        board[8] = 'O';
                        return true;
                    }
                }
            }
            return false;
        }
        
        public boolean getWinner() {
            if (board[4] == 'X') {
                if (board[0] == 'X' && board[8] == 'X') {
                    System.out.println("Congradulations, you've won!");
                    return true;
                } else if (board[1] == 'X' && board[7] == 'X') {
                    System.out.println("Congradulations, you've won!");
                    return true;
                } else if (board[2] == 'X' && board[6] == 'X') {
                    System.out.println("Congradulations, you've won!");
                    return true;
                } else if (board[3] == 'X' && board[5] == 'X') {
                    System.out.println("Congradulations, you've won!");
                    return true;
                }
            } 
            if (board[0] == 'X') {
                if (board[1] == 'X' && board[2] == 'X') {
                    System.out.println("Congradulations, you've won!");
                    return true;
                } else if (board[3] == 'X' && board[6] == 'X') {
                    System.out.println("Congradulations, you've won!");
                    return true;
                }
            } 
            if (board[8] == 'X') {
                if (board[2] == 'X' && board[5] == 'X') {
                    System.out.println("Congradulations, you've won!");
                    return true;
                } else if (board[7] == 'X' && board[6] == 'X') {
                    System.out.println("Congradulations, you've won!");
                    return true;
                }

            }
            return false;
        }

        public boolean getCompWinner() {
            if (board[4] == 'O') {
                if (board[0] == 'O' && board[8] == 'O') {
                    System.out.println("Sorry, you've lost!");
                    return true;
                } else if (board[1] == 'O' && board[7] == 'O') {
                    System.out.println("Sorry, you've lost!");
                    return true;
                } else if (board[2] == 'O' && board[6] == 'O') {
                    System.out.println("Sorry, you've lost!");
                    return true;
                } else if (board[3] == 'O' && board[5] == 'O') {
                    System.out.println("Sorry, you've lost!");
                    return true;
                }
            } else if (board[0] == 'O') {
                if (board[1] == 'O' && board[2] == 'O') {
                    System.out.println("Sorry, you've lost!");
                    return true;
                } else if (board[3] == 'O' && board[6] == 'O') {
                    System.out.println("Sorry, you've lost!");
                    return true;
                }
            } else if (board[8] == 'O') {
                if (board[2] == 'O' && board[5] == 'O') {
                    System.out.println("Sorry, you've lost!");
                    return true;
                } else if (board[7] == 'O' && board[6] == 'O') {
                    System.out.println("Sorry, you've lost!");
                    return true;
                }

            }

            return false;
        }

        public boolean getTie() {
            for (int i = 0; i <= 8; i++) {
                if (board[i] == ' ') {
                    return false;
                }
            }
            return true;
        }
    }
}
