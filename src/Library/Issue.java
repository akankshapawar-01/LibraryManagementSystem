package Library;

import java.sql.Date;

public class Issue {
    private int issueId;
    private int bookId;
    private int memberId;
    private Date issueDate;
    private Date returnDate;

    public Issue(int issueId, int bookId, int memberId, Date issueDate, Date returnDate) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getIssueId() {
        return issueId;
    }
    public int getBookId() {
        return bookId;
    }
    public int getMemberId() {
        return memberId;
    }
    public Date getIssueDate() {
        return issueDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
}
