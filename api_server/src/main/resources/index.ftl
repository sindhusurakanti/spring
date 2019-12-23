<#-- @ftlvariable name="" type="com.razorpay.PaymentView" -->
<html>
<head lang="en">
    <meta charset="utf-8">
</head>
<body>
<form action="/payment" method="POST">
    <script
           
            src="https://checkout.razorpay.com/v1/razorpay.js"
            
         
            
            data-key="rzp_test_caQ7OMKVvbtpc3"
            data-amount=${amount?c}
            data-order_id=${razorpayOrderId}
            data-name="sample"
            data-description="Purchase Description"
            data-image="vk.jpg"
            data-netbanking="true"
            data-description="sample"
            data-prefill.name="sample"
            data-prefill.email="sample@razorpay.com"
            data-prefill.contact="9999999999"
            data-notes.shopping_order_id="21">
    </script>
    <input type="hidden" name="shopping_order_id" value="21">
      
      <p>enter amount</p>
      <input type = "text" name = "amount"> </input>
      
      <p>select a payment option</p>
       <select name="payment option">
    <option value="debit card">debit card</option>
    <option value="credit card">credit card</option>
   
  </select>
  <input type="submit" value="Submit">
</form>
</body>
</html>