package com.like4u.server.infrastructrue.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/1 16:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SPage<T> {
    private List<T> list;
    private Long total;
}
