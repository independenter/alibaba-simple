package sup.base.jdbc;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

public class PagePlugin {

    public static void main(String[] args) {
        Page page = new Page();
        //System.out.println(PageProxy.queryAll(page));
        System.out.println("第二页");
        page.setCurrentPage(4);
        System.out.println(PageProxy.queryAll(page));
    }

    static class PageProxy {
        private Mybatils src;

        public static List queryAll(Page page) {
            return page.getResult();
        }
    }

    //    @Data
//    @ToString
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Accessors(chain = true)
    static class Page<T> {
        private int currentPage = 1;
        private int numPerPage = 5;
        private int totalCount;
        private int totalPageNum = 15;
        private List<T> obj;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(int numPerPage) {
            this.numPerPage = numPerPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPageNum() {
            return totalPageNum;
        }

        public void setTotalPageNum(int totalPageNum) {
            this.totalPageNum = totalPageNum;
        }

        public List<T> getObj() {
            return obj;
        }

        public void setObj(List<T> obj) {
            this.obj = obj;
        }

        public List<T> getResult() {
            try {
                StringBuilder sql = new StringBuilder("select * from b_role order by role_id ");
                if (currentPage * numPerPage <= totalPageNum) {
                    sql.append(" limit 0," + totalPageNum);
                    this.obj = Mybatils.execute(sql.toString());
                    return this.obj.subList((currentPage - 1) * numPerPage, currentPage * numPerPage);//0~5 5~10
                } else {
                    sql.append(" limit " + ((currentPage - 1) * numPerPage) + "," + totalPageNum);
                    this.obj = Mybatils.execute(sql.toString());
                    return this.obj.subList(0, numPerPage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
