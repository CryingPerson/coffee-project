package edu.example.coffeeproject.exception;

public enum MemberException {
    MEMBER_NOT_FOUND("Member is Empty",404),
    MEMBER_NOT_READ("Member is not Read",404),;

    private MemberTaskException memberTaskException;

     MemberException(String message, int code){
        memberTaskException = new MemberTaskException(message,code);
    }

    public MemberTaskException get(){
         return memberTaskException;
    }
}
