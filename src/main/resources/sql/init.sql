insert into snack_machine_dto (five_dollar_count, money_in_transaction,
                               one_cent_count, one_dollar_count,
                               quarter_count, ten_cent_count, twenty_dollar_count, id)
values (1, 0.0, 1, 1, 1, 1, 1, 1);

insert into snack_dto
values (1, 'Chocolate'),
       (2, 'Soda'),
       (3, 'Gum');

insert into slot_dto
values (1, 1, 0.5, 10, 1, 1),
       (2, 2, 1, 20, 2, 1),
       (3, 3, 2, 30, 3, 1);
