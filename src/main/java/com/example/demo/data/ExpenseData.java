package com.example.demo.data;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.Date;
public class ExpenseData {
        private String expenseId;
        private UserData user;
        private ItemData item;
        private int amount;
        private Double expensePrice;
        private Double totalExpenseValue;
        private Date expenseDateTime;


        public void setExpenseId(String expenseId) {
            this.expenseId = expenseId;
        }

        public void setUser(UserData user) {
            this.user = user;
        }

        public void setItem(ItemData item) {
            this.item = item;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setExpensePrice(double expensePrice) {
            this.expensePrice = expensePrice;
        }

        public String getExpenseId() {
            return expenseId;
        }

        public UserData getUser() {
            return user;
        }

        public ItemData getItem() {
            return item;
        }

        public int getAmount() {
            return amount;
        }

        public Double getExpensePrice() {
            return expensePrice;
        }

        public void setTotalExpenseValue(Double totalExpenseValue) {
            this.totalExpenseValue = totalExpenseValue;
        }

        public Double getTotalExpenseValue() {
            return totalExpenseValue;
        }

        public void setExpenseDateTime(Date expenseDateTime) {
            this.expenseDateTime = expenseDateTime;
        }

        public Date getExpenseDateTime() {
            return expenseDateTime;
        }
}


