package com.jia.bulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.bulu.entity.AddressBook;
import com.jia.bulu.mapper.AddressBookMapper;
import com.jia.bulu.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
