/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

/**
 *
 * @author Brianan Johnson Command Factory will take the command from the
 * individual command class Build it and implement it
 */
public class CommandFactory {

    public static Command createCommand(String action) {
        Command c = null;
        if (action == null) {
            action = "";
        }

        switch (action) {

            case "login":
                c = new LoginCommand();
                break;
            case "signUp":
                c = new SignUpCommand();
                break;
            case "viewUserDetails":
                c = new ViewUserDetailsCommand();
                break;
            case "changeUserDetails":
                c = new ChangeUserDetailsCommand();
                break;
                 case "displayBooksAndAuthors":
                c = new DisplayBooksAndAuthorsCommand();
                break;  
            case "borrowBook":
                c = new BorrowBookCommand();
                break;
            case "returnBook":
                c = new ReturnBookCommand();
                break;
            case "OverdueFees":
                c = new MyOverdueFeesCommand();
                break;
            case "searchBooks":
                c = new SearchBookCommand();
                break;
            case "displayBooks":
                c = new DisplayBooksCommand();
                break;
            case "viewCurrentLoans":
                c =  new ViewCurrentLoansCommand();
                break;
            case "viewPreviousLoans":
                c = new ViewPreviousLoansCommand();
                break;
            case "changeLanguage":
                c = new ChangeLanguageCommand();
                break;                
            case "logout":
                c = new LogoutCommand();
                break;
            default:
                c = new NoActionSuppliedCommand();

        }
        return c;

    }

}
