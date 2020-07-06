package com.ssm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.entity.Model;
import com.ssm.entity.PcUser;
import com.ssm.entity.SmCheck;
import com.ssm.entity.SmClean;
import com.ssm.entity.SmLogin;
import com.ssm.entity.SmReper;
import com.ssm.entity.SmServe;
import com.ssm.service.ISmAdviceService;
import com.ssm.service.ISmCheckService;
import com.ssm.service.ISmCleanService;
import com.ssm.service.ISmLoginService;
import com.ssm.service.ISmReperService;
import com.ssm.service.ISmServeService;
import com.ssm.service.ISmUserService;
import com.ssm.util.ExportExcelUtil;
import com.ssm.vo.res.SysResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 业务维修
 * </p>
 *
 * @author hello
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/serve")
public class SmServeController {

	@Autowired
	private ISmReperService iSmReperService;
	@Autowired
	private ISmLoginService iSmLoginService;
	@Autowired
	private ISmServeService iSmServeService;
	@Autowired
	private ISmCleanService iSmCleanService;
	@Autowired
	private ISmCheckService iSmCheckService;

	// 库存列表
	@PostMapping("/reperList")
	public SysResult reperList(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmReper> queryWrapper = new QueryWrapper<>();
			// 名称
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("title", pc.getName());
			}
			Page<SmReper> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmReper> list = iSmReperService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}

	}

	// 库存添加
	@PostMapping("/reperAdd")
	public SysResult reperAdd(@RequestBody SmReper pcUser) {
		try {
			QueryWrapper<SmReper> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("title", pcUser.getTitle());
			List<SmReper> rep = iSmReperService.list(queryWrapper);
			if (rep.size() > 0) {
				SmReper res = rep.get(0);
				res.setMoney(pcUser.getMoney());
				int num = res.getNum();
				int allNum = pcUser.getNum() + num;
				res.setNum(allNum);
				iSmReperService.saveOrUpdate(res);
			} else {
				iSmReperService.save(pcUser);
			}
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("添加失败！");
		}
	}

	// 车位列表
	@PostMapping("/carList")
	public SysResult carList(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			// 姓名
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			// 身份证号
			if (StringUtils.isNotEmpty(pc.getIdCard())) {
				queryWrapper.like("id_card", pc.getIdCard());
			}
			queryWrapper.eq("role", "2");
			queryWrapper.eq("status", "Y");
			Page<SmLogin> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmLogin> list = iSmLoginService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}
	}

	// 自助缴费列表
	@PostMapping("/payList")
	public SysResult payList(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			// 姓名
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			// 身份证号
			if (StringUtils.isNotEmpty(pc.getIdCard())) {
				queryWrapper.like("id_card", pc.getIdCard());
			}
			queryWrapper.eq("role", "2");
			queryWrapper.eq("status", "Y");
			Page<SmLogin> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmLogin> list = iSmLoginService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}
	}

	// 维修列表
	@PostMapping("/repairList")
	public SysResult repairList(@RequestBody PcUser pc) {
		try {
			QueryWrapper<SmServe> queryWrapper = new QueryWrapper<>();
			// 姓名
			if (StringUtils.isNotEmpty(pc.getName())) {
				queryWrapper.like("name", pc.getName());
			}
			// 电话
			if (StringUtils.isNotEmpty(pc.getMobile())) {
				queryWrapper.like("mobile", pc.getMobile());
			}
			// 角色
			if (StringUtils.isNotEmpty(pc.getRole())) {
				if ("1".equals(pc.getRole())) {
					// admin 全部数据
				} else if ("2".equals(pc.getRole())) {
					queryWrapper.eq("code", pc.getCode());
				} else if ("3".equals(pc.getRole())) {
					queryWrapper.eq("up_code", pc.getCode());
				}
			}
			Page<SmServe> page = new Page<>(pc.getCurrentPage(), pc.getPageSize());
			Page<SmServe> list = iSmServeService.page(page, queryWrapper);
			return SysResult.ok(list);
		} catch (Exception e) {
			return SysResult.fail("获取信息失败！");
		}
	}

	// 库存添加数据装载
	@PostMapping("/repairLoad")
	public SysResult repairLoad(@RequestBody SmServe pcUser) {
		try {
			// 业主数据列表
			QueryWrapper<SmLogin> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("role", "2").eq("status", "Y");
			List<SmLogin> user = iSmLoginService.list(queryWrapper);

			// 维修工
			QueryWrapper<SmLogin> query = new QueryWrapper<>();
			query.eq("role", "3").eq("status", "Y").eq("org", "后勤部");
			List<SmLogin> serve = iSmLoginService.list(query);

			// 库存查询
			List<SmReper> reper = iSmReperService.list();
			Model model = new Model();
			model.setUserdata(user);
			model.setReperdata(reper);
			model.setServedata(serve);
			return SysResult.ok(model);
		} catch (Exception e) {
			return SysResult.fail("添加失败！");
		}
	}

	// 维修添加
	@PostMapping("/repairAdd")
	public SysResult repairAdd(@RequestBody SmServe pcUser) {
		try {
			int num = pcUser.getNum();
			QueryWrapper<SmReper> query = new QueryWrapper<>();
			query.eq("title", pcUser.getThing());
			List<SmReper> list = iSmReperService.list(query);
			if (list.size() > 0) {
				Double s = list.get(0).getMoney();
				Double w = Double.valueOf(num);
				Double sw = s * w;
				int i = new Double(sw).intValue();
				pcUser.setMoney(i);

			}
			pcUser.setMoneyStatus(pcUser.getMoneyStatus());
			pcUser.setServeStatus(pcUser.getServeStatus());
			pcUser.setCreateDate(new Date());
			iSmServeService.save(pcUser);
			// 减少reper
			if (list.size() > 0) {
				SmReper rep = list.get(0);
				int newNum = rep.getNum() - num;
				rep.setNum(newNum);
				iSmReperService.saveOrUpdate(rep);
			}
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("添加失败！");
		}
	}

	@PostMapping("/download")
	public SysResult download(HttpServletResponse response) {
		QueryWrapper<SmClean> query = new QueryWrapper<>();
		query.eq("status", "Y");
		List<SmClean> list = iSmCleanService.list(query);
		// 列名
		String[] titles = { "序号", "姓名", "部门", "职位", "开始时间", "结束时间", "工作地点" };
		// 数据
		List<String[]> excelList = new ArrayList<String[]>();
		// 序号
		int i = 1;
		for (SmClean aa : list) {
			String[] data = new String[titles.length];
			data[0] = String.valueOf(i);
			data[1] = String.valueOf(aa.getName() == null ? "" : aa.getName());
			data[2] = String.valueOf(aa.getOrg() == null ? "" : aa.getOrg());
			data[3] = String.valueOf(aa.getWork() == null ? "" : aa.getWork());
			data[4] = String.valueOf(aa.getWorkTime() == null ? "" : aa.getWorkTime());
			data[5] = String.valueOf(aa.getEndTime() == null ? "" : aa.getEndTime());
			data[6] = String.valueOf(aa.getAddress() == null ? "" : aa.getAddress());
			excelList.add(data);
			i++;
		}
		ExportExcelUtil.export(response, String.format("安保保洁信息表"), String.format("安保保洁信息表"), titles, 20, excelList);
		return null;
	}

	@PostMapping("/downExp")
	public SysResult downExp(HttpServletResponse response) {
		// 列名
		String[] titles = { "姓名", "部门", "职位", "累计迟到次数", "初步早退次数", "初步旷工次数" };
		// 数据
		List<String[]> excelList = new ArrayList<String[]>();
		ExportExcelUtil.export(response, String.format("考勤管理模板"), "", titles, 20, excelList);
		return null;
	}

	@PostMapping("/doExpt")
	@ResponseBody
	public SysResult ExportInUserAction(MultipartHttpServletRequest request) throws IOException {
		try {
			// 模版数据: 姓名 部门 职位 累计迟到次数 初步早退次数 初步旷工次数
			MultipartFile file = request.getFile("file");
			InputStream ips = file.getInputStream();
			HSSFWorkbook wb = new HSSFWorkbook(ips);
			HSSFSheet sheet = wb.getSheetAt(0);
			DecimalFormat df = new DecimalFormat("#");
			int j = 0;
			for (Iterator ite = sheet.rowIterator(); ite.hasNext();) {
				HSSFRow row = (HSSFRow) ite.next();
				System.out.println();
				int i = 0;
				String sjrName = "";
				String sjrOrg = "";
				String sjrWork = "";
				String sjrLate = "";
				String sjrGo = "";
				String sjrNo = "";
				if (j == 0) {
					// 第一行跳过表头
					j++;
					continue;
				}
				for (Iterator itet = row.cellIterator(); itet.hasNext();) {
					HSSFCell cell = (HSSFCell) itet.next();
					// TODO 判断单元格是第几列
					if (i == 0) {
						// 姓名
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:// 数字
							sjrName = df.format(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:// 字符串
							sjrName = cell.getStringCellValue();
							break;
						default:
							sjrName = cell.toString();
							break;
						}
					}
					if (i == 1) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:// 数字
							sjrOrg = df.format(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:// 字符串
							sjrOrg = cell.getStringCellValue();
							break;
						default:
							sjrOrg = cell.toString();
							break;
						}
					}
					if (i == 2) {

						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:// 数字
							sjrWork = df.format(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:// 字符串
							// wuliuNo = df.format(Double.parseDouble(cell.toString()));
							sjrWork = cell.getStringCellValue();
							break;
						default:
							sjrWork = cell.toString();
							break;
						}
					}
					if (i == 3) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:// 数字
							sjrLate = df.format(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:// 字符串
							sjrLate = cell.getStringCellValue();
							break;
						default:
							sjrLate = cell.toString();
							break;
						}
					}
					if (i == 4) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:// 数字
							sjrGo = df.format(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:// 字符串
							sjrGo = cell.getStringCellValue();
							break;
						default:
							sjrGo = cell.toString();
							break;
						}
					}
					if (i == 5) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:// 数字
							sjrNo = df.format(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:// 字符串
							sjrNo = cell.getStringCellValue();
							break;
						default:
							sjrNo = cell.toString();
							break;
						}
					}
					i++;
				}
				// TODO 处理业务数据
				if (StringUtils.isNotEmpty(sjrName) && StringUtils.isNotEmpty(sjrOrg) && StringUtils.isNotEmpty(sjrWork)
						&& StringUtils.isNotEmpty(sjrLate) && StringUtils.isNotEmpty(sjrGo)
						&& StringUtils.isNotEmpty(sjrNo)) {
					SmCheck inuser = new SmCheck();
					inuser.setName(sjrName);
					inuser.setOrg(sjrOrg);
					inuser.setWork(sjrWork);
					inuser.setLateNum(Integer.parseInt(sjrLate));
					inuser.setEarlyNum(Integer.parseInt(sjrGo));
					inuser.setAbsentNum(Integer.parseInt(sjrNo));
					inuser.setStatus("Y");
					inuser.setCreateDate(new Date());
					iSmCheckService.save(inuser);
				}
				//
				j++;
			}
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("上传失败！");
		}

	}

	// 维修状态修改
	@PostMapping("/serveUpdate")
	public SysResult serveUpdate(@RequestBody SmServe pcUser) {
		try {
			QueryWrapper<SmServe> query = new QueryWrapper<>();
			query.eq("id", pcUser.getId());
			List<SmServe> list = iSmServeService.list(query);
			if (list.size() > 0) {
				SmServe rep = list.get(0);
				if (StringUtils.isNoneBlank(pcUser.getMoneyStatus())) {
					rep.setMoneyStatus(pcUser.getMoneyStatus());
				}
				if (StringUtils.isNoneBlank(pcUser.getServeStatus())) {
					rep.setServeStatus(pcUser.getServeStatus());
				}
				iSmServeService.saveOrUpdate(rep);
			}
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("修改失败！");
		}
	}

	// 车位和自助缴费状态修改
	@PostMapping("/paymoney")
	public SysResult paymoney(@RequestBody SmLogin pcUser) {
		try {
			QueryWrapper<SmLogin> query = new QueryWrapper<>();
			query.eq("id", pcUser.getId());
			List<SmLogin> list = iSmLoginService.list(query);
			if (list.size() > 0) {
				SmLogin rep = list.get(0);
				if (StringUtils.isNoneBlank(pcUser.getFeeStatus())) {
					rep.setFeeStatus(pcUser.getFeeStatus());
				}
				if (StringUtils.isNoneBlank(pcUser.getCarStatus())) {
					rep.setCarStatus(pcUser.getCarStatus());
				}
				iSmLoginService.saveOrUpdate(rep);
			}
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail("修改失败！");
		}
	}

}
