package com.dev.usersapi.entity;

public class Summary {

    private String uf;
    private long count;

    public Summary() {
    }

    public Summary(String uf, long sum) {
        this.uf = uf;
        this.count = sum;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Summary{" + "uf=" + uf + ", count=" + count + '}';
    }

}
