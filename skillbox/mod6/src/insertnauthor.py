bkid = 101
for aid in range(1,100):
    for b in range(1,30): 
        print('insert into book2author (id, book_id, author_id, sort_index) values ('+str(bkid)+', '+str(b)+', '+str(aid)+', -1);')
        bkid+=1
