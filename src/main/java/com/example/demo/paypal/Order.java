package com.example.demo.paypal;





    public class Order {

        private double price;
        private String currency;
        private String method;
        private String intent;
        private String description;
        private Integer quantity;

        public Order(double price, String currency, String method, String intent, String description, Integer quantity) {
            this.price = price;
            this.currency = currency;
            this.method = method;
            this.intent = intent;
            this.description = description;
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "price=" + price +
                    ", currency='" + currency + '\'' +
                    ", method='" + method + '\'' +
                    ", intent='" + intent + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }


