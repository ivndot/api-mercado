# Endpoints

### **Endpoint: /tables**

---

- **Allowed methods:** GET
- **Params:** None
- **Response:** A JSON containing an array of tables

> Response example with one table _"abono"_

```
{
  "tables": [
    {
      "columns": [
        {
          "dataType": "int",
          "values": [
            "53",
            "97",
            "123",
            "173",
            "234"
          ],
          "columnName": "idempresa"
        },
        {
          "dataType": "int",
          "values": [
            "6",
            "1",
            "2",
            "4",
            "3",
            "5",
            "7"
          ],
          "columnName": "idasiento"
        },
        {
          "dataType": "int",
          "values": [
            "10",
            "11",
            "1",
            "2",
            "3",
            "6",
            "7",
            "4",
            "5",
            "8",
            "9",
            "12",
            "13"
          ],
          "columnName": "idabono"
        },
        {
          "dataType": "int",
          "values": [
            "5",
            "6",
            "7",
            "8",
            "11"
          ],
          "columnName": "idcuenta"
        },
        {
          "dataType": "decimal",
          "values": [
            "1384.22",
            "495.34",
            "1879.42",
            "100.00",
            "10.00",
            "123123.00",
            "234.00"
          ],
          "columnName": "monto"
        }
      ],
      "tableName": "abono"
    },
  ],
  "code": 100,
  "description": "Se obtuvieron correctamente los datos",
  "status": "ok"
}
```

### **Endpoint: /proba**

---

- **Allowed methods:** GET
- **Params:**
  - **table:** The name of the table to execute the query
  - **field1:** First columns's name of the selected table
  - **valueField1:** First columns's value
  - **field2:** Second columns's name of the selected table
  - **valueField2:** Second columns's value
- **Response:** A JSON containing an object of probabilities (_bayesiana_,_conjunta_ y _condicional_)

> Response example with:
>
> - **table:** balances
> - **field1:** mercado
> - **valueField1:** 2
> - **field2:** industria
> - **valueField2:** 4

```
{
  "proba": {
    "bayesiana": "0.2692307692307692",
    "conjunta": "0.0350",
    "condicional": "0.15909091"
  },
  "code": 100,
  "description": "Se creo la vista y se obtuvieron las probabilidades correctamente",
  "status": "ok"
}
```
