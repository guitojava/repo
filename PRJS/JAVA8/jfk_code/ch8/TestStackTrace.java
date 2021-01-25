class TestStackTrace{    
    TestStackTrace()
	{
	  divideByZero();
	}

	int divideByZero()
	{
      return 25/0;
	}

	static void main(String[]args)
	{
	   new TestStackTrace();
	}
 }
